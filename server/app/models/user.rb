class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
         
  has_many :posts
  has_and_belongs_to_many :audiences, -> { uniq }
         
  def name
    given_name + " " + surname
  end

  def self.random
    User.offset(rand(User.count - 1)).first
  end
  
  def self.generate
    user = User.create :given_name => Faker::Name.first_name,
                       :surname    => Faker::Name.last_name,
                       :email      => Faker::Internet.email,
                       :image      => "user-#{rand(1..5)}.png",
                       :password   => "password",
                       :password_confirmation => "password"

    puts "Generating: User"
    puts "Name  = #{user.name}"
    puts "Email = #{user.email}"
  end

end
