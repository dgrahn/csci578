
class Result
  
  def self.result(code, message)
    {:code => code, :message => message}
  end

  LOGIN_FAIL     = result(1, "Login Failed")
  LOGIN_SUCCESS  = result(2, "Login Success")
  SIGNUP_FAIL    = result(3, "Signup Failed")
  SIGNUP_SUCCESS = result(4, "Signup Success")
  POST_FAIL      = result(5, "Post Failed")
  POST_SUCCESS   = result(6, "Post Success")
   
end