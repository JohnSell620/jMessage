import React from 'react';

class User extends React.Component {
  render() {
    return (
      <div className="user-inlist">
        <div className="user-info" onClick={() => this.highlightPost()}>
          {this.props.profileName}
        </div>
      </div>
    );
  }
  highlightPost() {
    if (this.postExists(this.props.profileName)) {
      // TODO
      console.log("TODO: highlight and center clicked user's latest post in thread...");
    } else {
      console.log("No posts...");
    }
  }
  postExists(user) {
    return true;
  }
}


export default User;
