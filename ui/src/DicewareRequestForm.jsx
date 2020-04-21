import React from "react";
import DicewareResponse from "./DicewareResponse";
import DicewareControls from "./DicewareControls";

export default class DicewareRequestForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            numberOfWords: props.numberOfWords || "5",
            mode: props.mode || "none"
        };
        this.handleChange = this.handleChange.bind(this);
        this.handlePasswordUpdate = this.handlePasswordUpdate.bind(this);
    }

    handlePasswordUpdate(password) {
        console.log("updating password into state...")
        this.setState({
            "password": password
        })
    }

    handleChange(event) {
        const name = event.target.name;
        const value = event.target.value;
        console.log("Updating state...", name + ":", value);
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

                    <div className="form-group">
                        <label htmlFor="concatMode">Concatenation mode</label>
                        <select id="concatMode"
                                className="form-control"
                                name="mode"
                                value={this.state.mode}
                                onChange={this.handleChange}>
                            <option value="flat">Flat</option>
                            <option value="camel">Camel</option>
                            <option value="snake">Snake</option>
                            <option value="kebab">Kebab</option>
                        </select>
                    </div>

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
