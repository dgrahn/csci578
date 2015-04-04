class CreateEmotions < ActiveRecord::Migration
  def change
    create_table :emotions do |t|
      t.string :emotion
      t.integer :user_id
      t.integer :post_id

      t.timestamps
    end
  end
end
