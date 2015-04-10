json.array!(@audiences) do |audience|
  json.extract! audience, :id, :name
  json.url audience_url(audience, format: :json)
end
