class Environment < ActiveRecord::Base
  
  belongs_to :post
  
  validates_presence_of :post
  validates_presence_of :sensor
  validates_presence_of :reading
  
  AMBIENT_TEMPERATURE = "Ambient Temperature" # Celsius
  LIGHT               = "Light"               # Lux
  PRESSURE            = "Pressure"            # Millibars
  RELATIVE_HUMIDITY   = "Relative Humidity"   # %
  
  ALL = [
    AMBIENT_TEMPERATURE,
    LIGHT,
    PRESSURE,
    RELATIVE_HUMIDITY
  ]
  
end
