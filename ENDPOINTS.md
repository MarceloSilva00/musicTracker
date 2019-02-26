# ENDPOINTS


### /genres  
   `GET`:
        Returns a list of all genres
        
        ?name={name} | OPTIONAL
   
   `POST`:
        Add a new genre        
        
   Body:
         
        {
            "genre": "{genreName}"
        }
        
***

### /genres/{genre_id}
   `Get`:
        Find a genre by id
        
   `PUT`:
        Updated genre
            
   Body:
      
        {
            "genre": "{genreName}"
        }    
   
***

### /albums     
   `GET`:
        Returns a list of all albums
        
         ?name={name} / ?sort= (gender || year) | OPTIONAL
   
   `POST`:
        Add a new album        
        
   Body:
         
        {
            "name": "{albumName}"
            "year": "{albumYear}"
        }
        
***

### /albums/{album_id}
   `GET`:
        Find a album by id
        
   `PUT`:
        Updated album
            
   Body:
      
        {
            "name": "{albumName}"
            "year": "{albumYear}"
        }
   
***
### /artists     
   `GET`:
        Returns a list of all artists
        
         ?name={name} | OPTIONAL
   
   `POST`:
        Add a new artist        
        
   Body:
         
        {
            "name": "{artistName}"
        }
        
***
### /artists/{artist_id}
   `GET`:
        Find a artist by id
        
   `PUT`:
        Updated artist
            
   Body:
      
        {
            "name": "{artistName}"
        }
        
***

 ### /tracks   
   
`GET`:
   Returns a list of all tracks   
      
    ?name={name} | OPTIONAL
    
   `POST`:
         Add a new track        
         
   Body:
          
         {
            "name":"{track_name}",
            "duration": {track_duration},
            "genreId": {track_genreId},
            "artistsIds":[
                {track_artistsIds} (1,6,10)
            ],
            "albumsIds": [
                {track_albumsIds} (3,5,8)
            ]
         }
         
 ***       
        
        
 ### /tracks/{track_id}   
   
   `GET`:
    Find a artist by id  
   `PUT`:
         Updated track       
         
   Body:
          
         {
            "name":"{track_name}",
            "duration": {track_duration},
            "genreId": {track_genreId},
            "artistsIds":[
                {track_artistsIds} (1,6,10)
            ],
            "albumsIds": [
                {track_albumsIds} (3,5,8)
            ]
         }
         
 ***       
                