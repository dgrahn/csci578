json.array!(@alerts) do |alert|
  json.extract! alert, :id, :user_id, :post_id
  json.url alert_url(alert, format: :json)
end
