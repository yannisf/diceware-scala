import React from "react";
import ProgressBar from 'react-bootstrap/ProgressBar';

export default class StrengthSelector extends React.Component {

    constructor(props) {
        super(props);
        this._getStrength = this._getStrength.bind(this);
    }

    _getStrength() {
        const pc = (this.props.numberOfWords - 2) * 10
        if (this.props.numberOfWords < 5) return {looks: "danger", label: "Weak", pc: pc}
        else if (this.props.numberOfWords > 7) return {looks: "success", label: "Strong", pc: pc}
        else return {looks: "info", label: "Sufficient", pc: pc}
    }

    render() {
        const strength = this._getStrength()
        return (
            <div className="form-group col-md-4">
                <label htmlFor="numberOfWords">Number of words</label>
                <input id="numberOfWords"
                       type="number" min="3" max="12"
                       className="form-control"
                       name="numberOfWords"
                       value={this.props.numberOfWords}
                       onChange={this.props.onChange}/>
                <ProgressBar variant={strength.looks} now={strength.pc} label={strength.label}/>
            </div>

        );
    }
}
