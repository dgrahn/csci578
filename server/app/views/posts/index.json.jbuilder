json.array!(@posts) do |post|
  json.extract! post, :id, :text, :user_id, :created_at
  #json.url post_url(post, :format => :json)
end
