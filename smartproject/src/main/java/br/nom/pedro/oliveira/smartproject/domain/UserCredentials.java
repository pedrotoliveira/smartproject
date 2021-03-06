/*
 *  Copyright (C) 2010 Pedro T. Oliveira <pedro.oliveira.nom.br>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.nom.pedro.oliveira.smartproject.domain;

import com.ppm.model.ValueObject;
import java.util.Objects;

/**
 * The User Credentials on the System
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public class UserCredentials extends ValueObject {

	private final UserToken token;
	private final AcessLevel accessLevel;

	public UserCredentials(final UserToken token, final AcessLevel accessLevel) {
		this.token = token;
		this.accessLevel = accessLevel;
	}

	public static UserCredentials newCredentials(final String token, final AcessLevel accessLevel) {
		return new UserCredentials(UserToken.newToken(token), accessLevel);
	}

	public static UserCredentials newCredentials(final UserToken token, final AcessLevel accessLevel) {
		return new UserCredentials(token, accessLevel);
	}

	public static UserCredentials blockedAccess() {
		return new UserCredentials(UserToken.blockedToken(), AcessLevel.NONE);
	}

	public static UserCredentials userNotExist() {
		return new UserCredentials(null, null);
	}

	public AcessLevel getAccessLevel() {
		return accessLevel;
	}

	public String getTokenValue() {
		return this.token.getValue();
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + Objects.hashCode(this.token);
		hash = 37 * hash + (this.accessLevel != null ? this.accessLevel.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserCredentials other = (UserCredentials) obj;
		if (!Objects.equals(this.token, other.token)) {
			return false;
		}
		if (this.accessLevel != other.accessLevel) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "UserCredentials{" + "token=" + token + ", accessLevel=" + accessLevel + '}';
	}
}
