#!/bin/bash -e
SCRIPT=$(readlink -f "${0}")
SCRIPT_DIR=$(dirname "${SCRIPT}")

if [ $# -lt 1 ]; then
	echo "Missing input file"
	exit 1
fi
INPUT="${1}"

if [ ! -f "${INPUT}" ]; then
	echo "${FILE_JSON} does not exist!"
	exit 2
fi

FILE_CSV="${SCRIPT_DIR}/games.csv"
FILE_JSON="${SCRIPT_DIR}/games.json"

COLUMNS=(
	"steam_appid" "type" "name" "required_age" "is_free" "header_image" "developers[]" "publishers[]"
	"price_overview.currency" "price_overview.initial" "price_overview.final" "price_overview.discount_percent"
	"platforms.windows" "platforms.mac" "platforms.linux"
	"pc_requirements.minimum" "pc_requirements.recommended" "mac_requirements.minimum" "mac_requirements.recommended" "linux_requirements.minimum" "linux_requirements.recommended"
	"metacritic.score" "metacritic.url"
	"recommendations.total"
	"release_date.coming_soon" "release_date.date"
)

COUNTER=0
echo '{"applications": [' > "${FILE_JSON}"
while read -r LINE; do
	IFS=',' read -ra ADDR <<< "${LINE}"
	if [ ${#ADDR[@]} -lt 2 ]; then
		continue
	fi
	ID="${ADDR[0]}"
	NAME="${ADDR[1]}"
	if [[ ! "${ID}" =~ ^[0-9]+$ ]]; then
		continue
	fi

	if [ ${COUNTER} -gt 0 ]; then
		echo "," >> "${FILE_JSON}"
	fi
        sleep .500
	echo "Processing ${NAME} (${ID})"
	declare -A MAP
	JSON=$(curl -s "https://store.steampowered.com/api/appdetails?appids=${ID}" | jq ".\"${ID}\".data")
	OUTPUT_LINE=""
	for COLUMN in "${COLUMNS[@]}"; do
		KEY=$(echo "${COLUMN}" | sed 's/\./_/g; s/[[]]//g; s/date_date/date/g; s/release_date_coming_soon/coming_soon/g')
		VALUE=$(echo "${JSON}" | jq -r ".${COLUMN}" 2>/dev/null || echo "")
		VALUE=$(echo "${VALUE}" | sed -e 's/<li>/\&emsp;/g; s/<br\/*>/\&#13;/g; s/<[^>]*>//g; s/\&#13;/<br>/g')

		MAP["${KEY}"]=$(echo "${VALUE}" | sed ':a;N;$!ba;s/\n/<br\/>/g')
	done
        
        if [ "${MAP["steam_appid"]}" == "" ]; then
            echo "Skipping ${NAME} (${ID})"  
            continue 
        fi
	
        VALUE=$(curl -s "https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v1/?appid=${ID}" | jq ".response.player_count" 2>/dev/null)
	if [ "${VALUE}" == "" ] || [ "${VALUE}" == "null" ] ; then
		MAP["current_players"]=0
	else
		MAP["current_players"]="$(echo -e "${VALUE}")"
	fi

	# CSV
	if [ ${COUNTER} -eq 0 ]; then
		INT_COUNTER=0
		for KEY in "${!MAP[@]}"; do
			if [ ${INT_COUNTER} -gt 0 ]; then
				echo -n ","
			fi
			echo -n "$KEY"
			INT_COUNTER=$((${INT_COUNTER}+1))
		done > "${FILE_CSV}"
		echo >> "${FILE_CSV}"
	fi
	INT_COUNTER=0
	for KEY in "${!MAP[@]}"; do
		if [ ${INT_COUNTER} -gt 0 ]; then
			echo -n ","
		fi
		VALUE=$(echo "${MAP[${KEY}]}" | sed 's/"//g' | sed ':a;N;$!ba;s/\n/ /g')
		if [[ $VALUE =~ ^[0-9]+$ ]]; then
			echo -n "${VALUE}"
		else
			if [ "${VALUE}"  != "" ] && [ "${VALUE}" != "null" ]; then 
				echo -n "\"${VALUE}\"" 
			fi
		fi
		INT_COUNTER=$((${INT_COUNTER}+1))
	done >> "${FILE_CSV}"
	echo >> "${FILE_CSV}"

	# JSON	
	for KEY in "${!MAP[@]}"; do
		echo "$KEY" 
    		echo "${MAP[${KEY}]}" | sed ':a;N;$!ba;s/\n/ /g'
	done | jq -n -R 'reduce inputs as $i ({}; . + { ($i): (input|(tonumber? // .)) })' >> "${FILE_JSON}"
	COUNTER=$((${COUNTER}+1))
done < "${INPUT}"
echo ']}' >> "${FILE_JSON}"

cat "${FILE_JSON}" | jq . > "${FILE_JSON}.tmp" && mv "${FILE_JSON}.tmp" "${FILE_JSON}"

