def favorite_genres(users, movies, movie_ratings):
  dict = {}
  user_list = []
  for i in movie_ratings:
    user_id = i["movie_id"]
    genre = find_movie_genre(user_id,movies)
    rating = i["rating"]
    
    if(dict.get(user_id)):
      if(dict.get(genre)):
        dict[user_id][genre].append(rating)
      else:
        dict[user_id][genre] = [rating]
    else:
      dict[user_id] = {genre: [rating]}
    
  for i in dict.items():
    user_id = i[0]
    favorite = find_favorite(i[1])
    
    user_list.append({"id": user_id, "favorite_genre": favorite})
  
  return user_list

def find_favorite(genres):
  favorite_genre = ""
  highest_avg_rating = -1
  
  for i in genres.items():
    values = i[1]
    total = 0
    genre = i[0]
    
    for i in values:
      total+= i
    
    if(highest_avg_rating < total/len(values)):
      highest_avg_rating = total/len(values)
      favorite_genre = genre
    
    return favorite_genre

def find_movie_genre(movie_id, movies):
  for i in movies:
    if(movie_id == i["id"]):
      return i["genre"]
  

print("start")
users = [{id: "923874rksd9293"}];
movie_ratings = [{"movie_id": "20jfcf02341kwd","user_id": "923874rksd9293", "rating": 5},{"movie_id": "20jfcf02341kwc","user_id": "923874rksd9293", "rating": 4}];
movies = [{"id": "20jfcf02341kwd","genre": "animated"},{"id": "20jfcf02341kwc","genre": "action"}];

print(favorite_genres(users, movies, movie_ratings))