import React from 'react';
import User from './User';

class UserList extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    const users = this.props.users.map(user =>
      <User key={user._links.self.href} userProfile={user}/>
    );
    return (
      <div className="user-list">
        {users}
        <div>UserListTest</div>
      </div>
    )
  }
}

export default UserList;
