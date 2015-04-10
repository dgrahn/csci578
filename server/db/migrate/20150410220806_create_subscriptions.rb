class CreateSubscriptions < ActiveRecord::Migration
  def change
    create_table :subscriptions do |t|
      t.integer :source_user_id
      t.integer :target_user_id

      t.timestamps
    end
  end
end
