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
        return (
            <div className="alert alert-dark" role="alert">
                <h6>Password</h6>
                <h4 className="mt-3">
                    {!this.state.hidden && <pre>{this.props.password} <BsEyeSlash title="hide" style={{cursor: 'pointer'}} onClick={this.handleClick}/></pre>}
                    {this.state.hidden && <pre>{this.props.password.replace(/./gi, "\u2022")} <BsEye title="show" style={{cursor: 'pointer'}} onClick={this.handleClick}/></pre>}
                </h4>
            </div>
        );
    }
}
