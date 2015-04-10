class CreateEnvironments < ActiveRecord::Migration
  def change
    create_table :environments do |t|
      t.string  :sensor
      t.decimal :reading
      t.integer :post_id

      t.timestamps
    end
  end
end
