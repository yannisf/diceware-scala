import React from "react";
import DicewareResponse from "./DicewareResponse";
import DicewareControls from "./DicewareControls";
import StrengthSelector from "./StrengthSelector";
import ModeSelector from "./ModeSelector";
import NumberOfPasswordsSelector from "./NumberOfPasswordsSelector";
import SpecialSwitches from "./SpecialSwitches";

export default class DicewareRequestForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            numberOfWords: props.numberOfWords || "5",
            mode: props.mode || "none",
            numberOfPasswords: 1,
            requireDigit: false,
            requireSpecial: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.handlePasswordUpdate = this.handlePasswordUpdate.bind(this);
        this.marshalParams = this.marshalParams.bind(this);
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value || event.target.checked
        })
    }

    handlePasswordUpdate(passwords) {
        this.setState({
            "passwords": passwords
        });
    }

    marshalParams() {
        return {
            words: this.state.numberOfWords,
            mode: this.state.mode,
            number: this.state.numberOfPasswords,
            digit: this.state.requireDigit,
            special: this.state.requireSpecial
        }
    }

    render() {
        return (
            <div>

                <form className="mb-3">

                    <div className="form-row">
                        <StrengthSelector numberOfWords={this.state.numberOfWords} onChange={this.handleChange}/>
                        <ModeSelector mode={this.state.mode} onChange={this.handleChange}/>
                        <NumberOfPasswordsSelector numberOPasswords={this.state.numberOfPasswords}
                                                   onChange={this.handleChange}/>
                    </div>

                    <div className="form-row">
                        <SpecialSwitches requireDigit={this.state.requireDigit}
                                         requireSpecial={this.state.requireSpecial} onChange={this.handleChange}/>
                    </div>

                    <DicewareControls params={this.marshalParams()}
                                      canClear={this.state.passwords && this.state.passwords.length > 0} onPasswordUpdate={this.handlePasswordUpdate}/>
                </form>

                {this.state.passwords && this.state.passwords.length > 0 && <DicewareResponse passwords={this.state.passwords}/>}
            </div>
        );
    }
}
