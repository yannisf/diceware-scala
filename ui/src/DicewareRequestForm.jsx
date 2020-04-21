import React from "react";
import DicewareResponse from "./DicewareResponse";
import DicewareControls from "./DicewareControls";
import ModeSelector from "./ModeSelector";

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
