json.array!(@subscriptions) do |subscription|
  json.extract! subscription, :id, :source_user_id, :target_user_id
  json.url subscription_url(subscription, format: :json)
end
