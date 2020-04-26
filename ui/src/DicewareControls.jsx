import React from "react";
import axios from "axios";
import {FaBroom, FaBolt} from "react-icons/fa"

export default class DicewareControls extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleClear = this.handleClear.bind(this);
    }

    handleSubmit() {
        axios.get("generate", {
            params: this.props.params
        }).then(response => this.props.onPasswordUpdate(response.data.map(p => p.password)))
    }

    handleClear() {
        this.props.onPasswordUpdate([])
    }

    render() {
        return (
            <div>
                <button type="button" className="btn btn-primary mr-1"
                        onClick={this.handleSubmit}>
                    <FaBolt/> Generate
                </button>
                <button type="button" className="btn btn-secondary"
                        onClick={this.handleClear} disabled={!this.props.canClear}>
                    <FaBroom/> Clear
                </button>
            </div>
        );
    }

}
