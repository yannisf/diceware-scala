import React from "react";
import { BsEye, BsEyeSlash } from 'react-icons/bs';

export default class DicewareResponse extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            hidden: true
        }
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        this.setState({
            hidden: !this.state.hidden
        });
    }

    render() {
        const passwords = this.props.passwords.map(password =>
            <h4 className="mt-3" key={password}>
                {!this.state.hidden && <pre>{password}</pre>}
                {this.state.hidden && <pre>{password.replace(/./gi, "\u2022")}</pre>}
            </h4>);

        return (
            <div className="alert alert-dark" role="alert">
                <h5>
                    Password &nbsp;
                    {this.state.hidden &&<BsEyeSlash title="show" style={{cursor: 'pointer'}} onClick={this.handleClick}/>}
                    {!this.state.hidden &&<BsEye title="hide" style={{cursor: 'pointer'}} onClick={this.handleClick}/>}
                </h5>
                {passwords}
            </div>
        );
    }
}
