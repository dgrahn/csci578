json.array!(@emotions) do |emotion|
  json.extract! emotion, :id, :emotion, :user_id, :post_id
  json.url emotion_url(emotion, format: :json)
end
