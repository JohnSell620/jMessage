import React from 'react';

class Message extends React.Component {
  render() {
    return(
      <div className="message-content">
        <span className="message-author">{this.props.author} - </span>
        <span className="message-time">{Date(this.props.created)}</span>
        <div className="message-text"
          onClick={() => this.linkToComment()}>
          {this.props.message}
        </div>
      </div>
    )
  }

  linkToComment() {
    console.log("TODO: Paste link in text form to desired message's user...");
  }
}

export default Message;
