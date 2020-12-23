import React from 'react';

class Message extends React.Component {
  render() {
    return(
      <div className={messageClass}>
        <div className="message-content">
          <span className="message-author">{message.author} @ </span>
          <span className="message-time">{message.created}</span>
          <div className="message-text">
            {message.message}
          </div>
        </div>
      </div>
    )
  }
}
