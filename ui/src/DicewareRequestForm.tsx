import * as React from "react";
import * as axios from "axios";

type DicewareRequestFormProps = {
    numberOfWords?: number,
    mode?: string,
    password? : string
}

export default class DicewareRequestForm extends React.Component<DicewareRequestFormProps, DicewareRequestFormProps> {

    #state: DicewareRequestFormProps;

    constructor(props: DicewareRequestFormProps) {
        super(props);
        this.#state = {
            numberOfWords: props.numberOfWords || 5,
            mode: props.mode || "none"
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleClear = this.handleClear.bind(this);
    }

    handleChange(event: React.ChangeEvent<HTMLSelectElement|HTMLInputElement>) {
        const name = event.currentTarget.name;
        const value = event.currentTarget.value;
        console.log("Updating state...", name + ":", value);
        this.setState({
            [name]: value
        });
    }

    handleSubmit(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        console.log("Submitting...");
        axios.default.get("http://localhost:8888/generate", {
            params: {
                words: this.state.numberOfWords,
                mode: this.state.mode
            }
        }).then(data => this.setState({password: data.data.password}))
    }

    handleClear(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        this.setState({password: ""})
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

                    <button type="button"
                            className="btn btn-primary mr-1"
                            onClick={this.handleSubmit}>
                        Generate
                    </button>

                    <button type="button"
                            className="btn btn-secondary"
                            onClick={this.handleClear} disabled={!this.state.password}>
                        Clear
                    </button>

                </form>

                {this.state.password &&
                <div className="card bg-light mb-3">
                    <div className="card-header">The password is</div>
                    <div className="card-body">
                        <pre>{this.state.password}</pre>
                    </div>
                </div>
                }
            </div>
        );
    }
}
