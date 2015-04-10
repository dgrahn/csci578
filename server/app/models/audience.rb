class Audience < ActiveRecord::Base
  
  has_many :posts
  has_and_belongs_to_many :users, -> { uniq }
  
  # Gets random audience
  def self.random
    Audience.offset(rand(Audience.count)).first
  end
  
  def self.generate
    audience = Audience.create :name => Faker::Team.name
    
    audience.users << User.find_by(:given_name => "Dan", :surname => "Grahn")
   
    rand(1..User.count).times do
      audience.users << User.random
    end
  end
  
end
