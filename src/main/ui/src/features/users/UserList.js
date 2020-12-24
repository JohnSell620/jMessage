import React from 'react';
import User from './User';

class UserList extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    const users = this.props.users.map(user =>
      <User key={user.id} profileName={user.profileName}/>
    );
    return (
      <div className="user-list">
        {users}
      </div>
    )
  }
}

export default UserList;
