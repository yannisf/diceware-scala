import React from "react";
import ReactDOM from "react-dom";
import DicewareRequestForm from './DicewareRequestForm';
import DictionaryDownload from './DictionaryDownload';

import './styles/index.scss';

ReactDOM.render(<DictionaryDownload/>, document.querySelector('#download'));
ReactDOM.render(<DicewareRequestForm numberOfWords="5" mode="camel"/>, document.querySelector('#app'));
