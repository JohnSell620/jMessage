import React from 'react';
import Message from './Message';
import './Conversation.scss'

class Conversation extends React.Component {
  render() {
    const messages = this.props.messages.map(message =>
      <Message
        key={message.id}
        message={message.message}
        author={message.author}
        created={message.created}
      />
    );
    return (
      <div className="conversation">
        <div className="conversation-header">Conversation</div>
        {messages}
        <div className="enter-text-window">
    			<input type="text" placeholder="Post text..." name="post" />
    			<input type="submit" value="Post"/>
        </div>
      </div>
    )
  }
}

export default Conversation;
