json.array!(@pals) do |pal|
  json.extract! pal, :id, :source_user_id, :target_user_id
  json.url pal_url(pal, format: :json)
end
