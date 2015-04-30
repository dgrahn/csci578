# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

u1 = User.create! :given_name => "Dan",
                  :surname    => "Grahn",
                  :image      => "dan.png",
                  :email      => "dan@grahn.us",
                  :password   => "licorice",
                  :password_confirmation => "licorice"

u2 = User.create! :given_name => "Gwendolyn",
                  :surname    => "Grahn",
                  :image      => "user-2.png",
                  :email      => "gwendolyn@grahn.us",
                  :password   => "licorice",
                  :password_confirmation => "licorice"

u3 = User.create! :given_name => "Anonymous",
                  :surname    => "Anonymous",
                  :image      => "user-1.png",
                  :email      => "anonymous@anonymous.com",
                  :password   => "Q7urhecgY9",
                  :password_confirmation => "Q7urhecgY9"

Pal.create :source => u1, :target => u2
Pal.create :source => u2, :target => u1

Subscription.create :source => u1, :target => u2

Post.create :user => u2, :text => "Hey all!"
            
hc = Audience.create :name => "House Church"
hc.users << u1
hc.users << u2