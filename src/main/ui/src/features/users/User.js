import React from 'react';

class User extends React.Component {
  render() {
    return (
      <div className="user-inlist">
        <span className="user-info">{this.props.user.profileName}</span>
        <button onClick={() => this.onChat()}>Chat</button>
      </div>
    );
  }
  onChat() {
    console.log("Creating new chat thread");
  }
}


export default User;
