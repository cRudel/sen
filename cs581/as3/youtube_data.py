# Author:  Cheryl Dugas

#  youtube_data.py searches YouTube for videos matching a search term

# To run from terminal window:   python3 youtube_data.py 

from apiclient.discovery import build      # use build function to create a service object

import unidecode   #  need for processing text fields in the search results

# put your API key into the API_KEY field below, in quotes
API_KEY = "AIzaSyBO5yrWJ2QNEqxKzoW5mTgZ6_4a5Xf7tNE"

API_NAME = "youtube"
API_VERSION = "v3"       # this should be the latest version

#  function youtube_search retrieves the YouTube records

def youtube_search(s_term, s_max):
    youtube = build(API_NAME, API_VERSION, developerKey=API_KEY)

    search_response = youtube.search().list(q=s_term, part="id,snippet", maxResults=s_max).execute()
    
    # search for videos matching search term;
    
    for search_result in search_response.get("items", []):
        if search_result["id"]["kind"] == "youtube#video":
            title = search_result["snippet"]["title"]
            title = unidecode.unidecode(title)  
            videoId = search_result["id"]["videoId"]
            video_response = youtube.videos().list(id=videoId,part="statistics").execute()
            for video_results in video_response.get("items",[]):
                viewCount = video_results["statistics"]["viewCount"]
                if 'likeCount' not in video_results["statistics"]:
                    likeCount = 0
                else:
                    likeCount = video_results["statistics"]["likeCount"]
                if 'dislikeCount' not in video_results["statistics"]:
                    dislikeCount = 0
                else:
                    dislikeCount = video_results["statistics"]["dislikeCount"]
                if 'commentCount' not in video_results["statistics"]:
                    commentCount = 0
                else:
                    commentCount = video_results["statistics"]["commentCount"]
            
            print("")    
            print(title,videoId,viewCount,likeCount,dislikeCount,commentCount)

# main routine

search_term = "baby"
search_max = 20

youtube_search(search_term, search_max)
