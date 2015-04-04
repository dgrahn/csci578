class AddColumnsToUsers < ActiveRecord::Migration
  def change
    add_column :users, :given_name, :string
    add_column :users, :surname,    :string
  end
end
