json.array!(@posts) do |post|
  json.extract! post, :id, :text, :user_id, :created_at
  json.emotions post.emotions, :user_id, :emotion
  
  #json.array!(post.emotions) do |emotion|
  #  json.extract! emotion, :user_id, :emotion
  #end
end
