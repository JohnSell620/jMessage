import React from 'react';

class User extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div className="user-inlist">
        <div className="user-info">
          {this.props.profileName}
          <button onClick={() => this.onChat()}>Chat</button>
        </div>
      </div>
    );
  }
  onChat() {
    if (this.isExistingConversation(this.props.profileName)) {
      // TODO
      console.log("TODO");
    } else {
      console.log("Creating new chat thread");
    }
  }
  isExistingConversation(user) {
    return true;
  }
}


export default User;
