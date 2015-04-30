class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
         
  has_many :posts
  has_many :emotions
  has_many :environments, :through => :posts
  has_and_belongs_to_many :audiences, -> { uniq }
  
  has_and_belongs_to_many :pals,
                          :class_name => 'User',
                          :join_table => :pals,
                          :foreign_key => :source_user_id,
                          :association_foreign_key => :target_user_id

  has_and_belongs_to_many :subscriptions,
                          :class_name => 'User',
                          :join_table => :subscriptions,
                          :foreign_key => :source_user_id,
                          :association_foreign_key => :target_user_id

  has_and_belongs_to_many :subscribers,
                          :class_name => 'User',
                          :join_table => :subscriptions,
                          :foreign_key => :target_user_id,
                          :association_foreign_key => :source_user_id
                          
  has_many :alerts
  
  before_save :ensure_authentication_token
  before_save :ensure_image
  
  # Get the anonymous user
  def self.anonymous
    User.find_by_email("anonymous@anonymous.com")
  end
 
  def ensure_authentication_token
    if authentication_token.blank?
      self.authentication_token = generate_authentication_token
    end
  end
  
  def ensure_image
    if image.blank?
      self.image = "user-#{rand(1..5)}.png"
    end
  end
         
  def name
    if surname == "Anonymous"
      surname
    else
      given_name + " " + surname
    end
  end

  def self.random
    User.offset(rand(User.count)).first
  end
  
  def self.generate
    user = User.create :given_name => Faker::Name.first_name,
                       :surname    => Faker::Name.last_name,
                       :email      => Faker::Internet.email,
                       :password   => "password",
                       :password_confirmation => "password"

    puts "Generating: User"
    puts "Name  = #{user.name}"
    puts "Email = #{user.email}"
  end
  
  # Checks if the user is pals with the specified user
  def pals?(user)
    self == user || pals.any? { |p| p == user}
  end

private
  
  def generate_authentication_token
    loop do
      token = Devise.friendly_token
      break token unless User.where(authentication_token: token).first
    end
  end

end
