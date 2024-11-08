package az.qonaqol.qonaqol.constants;

import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.model.dto.AuthenticationDto;
import az.qonaqol.qonaqol.model.enums.UserRole;
import az.qonaqol.qonaqol.model.request.SigninRequest;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import az.qonaqol.qonaqol.security.jwt.TokenPair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public class TestConstants {

    public static final String EMAIL = "ABC1234";
    public static final String PASSWORD = "abc12345";
    public static final String FULL_NAME = "Ilgar Mustafayev";


    public static final SigninRequest VALID_USER_LOGIN = new SigninRequest(EMAIL, PASSWORD);
    public static final SignupRequest VALID_USER_REGISTRATION = new SignupRequest(FULL_NAME, EMAIL, PASSWORD, PASSWORD);
    public static final SigninRequest INVALID_USER_LOGIN = new SigninRequest("invalid-user", "wrong-password");

    public static final String VALID_ACCESS_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXNoZmlxYXplcmlAZ21haWwuY29tIiwiYXV0aCI6IlJPTEVfQURNSU4sYWNjZXB0X29yZGVyLGFkZF9wZXJtaXNzaW9uLGFkZF9yb2xlLGFkZF91c2VyLGFzc2lnbl9jb3VyaWVyLGNhbmNlbF9vcmRlcixjaGFuZ2Vfb3JkZXJfc3RhdHVzLGNoYW5nZV9wYXJjZWxfc3RhdHVzLGNyZWF0ZV9jb3VyaWVyLGRlbGV0ZV9wZXJtaXNzaW9uLGRlbGV0ZV9yb2xlLGRlbGV0ZV91c2VyLHRyYWNrX3BhcmNlbCx1cGRhdGVfb3JkZXIsdXBkYXRlX3BhcmNlbCx1cGRhdGVfcGVybWlzc2lvbix1cGRhdGVfcm9sZSx1cGRhdGVfdXNlcix2aWV3X2NvdXJpZXIsdmlld19vcmRlcix2aWV3X3BhcmNlbCx2aWV3X3Blcm1pc3Npb24sdmlld19yb2xlLHZpZXdfdXNlcix2aWV3X3VzZXJfbGlzdCIsInRva2VuX3R5cGUiOiJBQ0NFU1MiLCJmdWxsX25hbWUiOiJNdXNoZmlxIE1hbW1hZG92IiwidXNlcl90eXBlIjoiQURNSU4iLCJleHAiOjI1ODI3ODQ4NDZ9.QTgRGY4AeJuqY92tU3JykeGTBTJLjod-HoYCcjyXL2y3cusIG6mYdE3N5ofUFOyts-BE_RQuMUdrYv5iXuv2rg";
    public static final String VALID_REFRESH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtdXNoZmlxYXplcmlAZ21haWwuY29tIiwiYXV0aCI6IlJPTEVfQURNSU4sYWNjZXB0X29yZGVyLGFkZF9wZXJtaXNzaW9uLGFkZF9yb2xlLGFkZF91c2VyLGFzc2lnbl9jb3VyaWVyLGNhbmNlbF9vcmRlcixjaGFuZ2Vfb3JkZXJfc3RhdHVzLGNoYW5nZV9wYXJjZWxfc3RhdHVzLGNyZWF0ZV9jb3VyaWVyLGRlbGV0ZV9wZXJtaXNzaW9uLGRlbGV0ZV9yb2xlLGRlbGV0ZV91c2VyLHRyYWNrX3BhcmNlbCx1cGRhdGVfb3JkZXIsdXBkYXRlX3BhcmNlbCx1cGRhdGVfcGVybWlzc2lvbix1cGRhdGVfcm9sZSx1cGRhdGVfdXNlcix2aWV3X2NvdXJpZXIsdmlld19vcmRlcix2aWV3X3BhcmNlbCx2aWV3X3Blcm1pc3Npb24sdmlld19yb2xlLHZpZXdfdXNlcix2aWV3X3VzZXJfbGlzdCIsInRva2VuX3R5cGUiOiJSRUZSRVNIIiwiZnVsbF9uYW1lIjoiTXVzaGZpcSBNYW1tYWRvdiIsInVzZXJfdHlwZSI6IkFETUlOIiwiZXhwIjozMjEzNTA0ODQ2fQ.tweKZYIHEkq9v5pLZK55-QpSw9vB8fi-syyER7WTUQ9iVShhasqwqXtFipxttMfljUXvpb5B3YRo2Qp7a_QyYw";

    public static final String INVALID_ACCESS_TOKEN = "invalid.access.token";

    public static final TokenPair TOKEN_PAIR = new TokenPair(VALID_ACCESS_TOKEN, VALID_REFRESH_TOKEN);


    public static final AuthenticationDto AUTHENTICATION_DTO = new AuthenticationDto(1L, TOKEN_PAIR);
    public static final TokenPair NEW_TOKEN_PAIR = new TokenPair("new.access.token", "new.refresh.token");
    public static final Authentication AUTHENTICATION = new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD, Set.of(new SimpleGrantedAuthority("ROLE_USER")));

}

