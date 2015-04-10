class CreateAudiences < ActiveRecord::Migration
  def change
    
    # Create audiences
    create_table :audiences do |t|
      t.string :name

      t.timestamps
    end
    
    # Audience/user relation
    create_table :audiences_users do |t|
      t.integer :user_id
      t.integer :audience_id
      
      t.timestamps
    end
    
    # Add audience to post
    add_column :posts, :audience_id, :integer
  end
end
