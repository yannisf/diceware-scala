import React from "react";
import axios from "axios";

export default class ModeSelector extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            modes: []
        }
        this._loadModes();
    }

    _loadModes() {
        axios.get("modes")
            .then(data => this.setState({modes: data.data}))
    }

    render() {
        const options = this.state.modes.map(mode =>
            <option key={mode.code} value={mode.code}>
                {mode.label}
            </option>);

        return (
            <div className="form-group col-md-4">
                <label htmlFor="concatMode">Concatenation mode</label>
                <select id="concatMode"
                        className="form-control"
                        name="mode"
                        value={this.props.mode}
                        onChange={this.props.onChange}>
                    {options}
                </select>
            </div>
        );
    }
}
