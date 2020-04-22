import React from "react";
import Dropdown from "react-bootstrap/Dropdown";
import { FaBook } from 'react-icons/fa';

export default class DictionaryDownload extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Dropdown>
        <Dropdown.Toggle variant="primary" id="dropdown-basic"><FaBook/> Dictionary</Dropdown.Toggle>
        <Dropdown.Menu>
          <Dropdown.Item href="/wordlist?download">Download</Dropdown.Item>
          <Dropdown.Item href="wordlist">View</Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
    );
  }

}
