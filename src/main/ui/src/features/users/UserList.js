import React from 'react';
import User from './User';
import './Users.scss';

class UserList extends React.Component {
  render() {
    const users = this.props.users.map(user =>
      <User key={user.id} profileName={user.profileName}/>
    );
    return (
      <div className="user-list">
        <div className="user-list-header">Thread's Users</div>
        {users}
      </div>
    )
  }
}

export default UserList;
