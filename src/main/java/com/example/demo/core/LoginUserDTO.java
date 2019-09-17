package com.example.demo.core;

/**
 * DTO for Login User.
 *
 * @author Virtus
 */
public class LoginUserDTO {

	/**
	 * ID.
	 */
	private Integer id;
	
    /**
     * Name.
     */
    private String name;

    /**
     * Login.
     */
    private String login;

    /**
     * Token.
     */
    private String token;

    /**
     * Refresh token.
     */
    private String refreshToken;

    /**
     * Constructor.
     */
    public LoginUserDTO() {
	}
    
    /**
     * Constructor.
     * 
     * @param id
     * @param login
     */
    public LoginUserDTO(Integer id, String login) {
		super();
		
		this.id = id;
		this.login = login;
	}

	/**
     * Gets the ID.
     * 
     * @return ID.
     */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the ID.
	 * 
	 * @param id
	 * 		ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
    
    /**
     * Gets the name.
     *
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the login.
     *
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the Login.
     *
     * @param login New Login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the token.
     *
     * @return Token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token Token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the refresh token.
     *
     * @return Refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Sets the refresh token.
     *
     * @param refreshToken Refresh token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
