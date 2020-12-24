import React from 'react';
import User from './User';

class UserList extends React.Component {
  render() {
    const users = this.props.users.map(user =>
      <User key={user.id} profileName={user.profileName}/>
    );
    return (
      <div className="user-list">
        <div className="user-list-header">Threads</div>
        {users}
      </div>
    )
  }
}

export default UserList;
