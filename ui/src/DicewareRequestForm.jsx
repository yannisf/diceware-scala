import React from "react";
import DicewareResponse from "./DicewareResponse";
import DicewareControls from "./DicewareControls";
import ModeSelector from "./ModeSelector";
import ProgressBar from 'react-bootstrap/ProgressBar';

export default class DicewareRequestForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            numberOfWords: props.numberOfWords || "5",
            mode: props.mode || "none"
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleModeUpdate = this.handleModeUpdate.bind(this);
        this.handlePasswordUpdate = this.handlePasswordUpdate.bind(this);
        this._getStrength = this._getStrength.bind(this);
    }

    _getStrength() {
        const pc = (this.state.numberOfWords - 2) * 10
        if (this.state.numberOfWords < 5) return { looks: "danger", label: "Weak", pc: pc }
        else if (this.state.numberOfWords > 7) return { looks: "success", label: "Strong", pc: pc}
        else return {looks: "info", label: "Sufficient", pc: pc}
    }

    handleModeUpdate(event) {
        this.setState({
            "mode": event.target.value
        })
    }

    handlePasswordUpdate(password) {
        this.setState({
            "password": password
        })
    }

    handleChange(event) {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({
            [name]: value
        });
    }

    render() {
        const strength = this._getStrength()
        return (
            <div>
                <form className="mb-3">
                    <div className="form-group">
                        <label htmlFor="numberOfWords">Number of words</label>
                        <input id="numberOfWords"
                               type="number" min="3" max="12"
                               className="form-control"
                               name="numberOfWords"
                               value={this.state.numberOfWords}
                               onChange={this.handleChange}/>
                        <ProgressBar variant={strength.looks} now={strength.pc} label={strength.label}/>
                      </div>

                    <ModeSelector mode={this.state.mode} onModeUpdate={this.handleModeUpdate}/>

                    <DicewareControls
                        numberOfWords={this.state.numberOfWords}
                        mode={this.state.mode}
                        canClear={!!this.state.password}
                        onPasswordUpdate={this.handlePasswordUpdate}/>

                </form>

                {this.state.password && <DicewareResponse password={this.state.password}/>}
            </div>
        );
    }
}
