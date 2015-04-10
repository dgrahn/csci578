json.array!(@environments) do |environment|
  json.extract! environment, :id, :type, :reading, :post_id
  json.url environment_url(environment, format: :json)
end
