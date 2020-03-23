import React from "react";
import ReactDOM from "react-dom";
import DicewareRequestForm from './DicewareRequestForm';

import './styles/index.scss';

ReactDOM.render(<DicewareRequestForm numberOfWords="5" mode="camel"/>, document.querySelector('#app'));
