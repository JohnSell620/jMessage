import React from 'react';
import './Button.scss';
import PropTypes from 'prop-types';

class Button extends React.Component {
  constructor(props) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick() {
    this.props.getConversationMessages(this.props.label);
    this.props.getConversationUsers(this.props.label);
  }

  render() {
    const label = this.props.label;
    return (
      <button
        className="default-button"
        onClick={this.handleClick}>
        {label}
      </button>
    );
  }
}

Button.defaultProps = {
  label: 'Thread'
};

Button.propTypes = {
  label: PropTypes.string.isRequired
};

export default Button;
