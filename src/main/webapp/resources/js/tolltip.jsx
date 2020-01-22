const Tooltip = ReactToolTipLite.default;

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = { tipOpen: false };

        this.toggleTip = this.toggleTip.bind(this);
        this.bodyClick = this.bodyClick.bind(this);
    }

    componentDidMount() {
        document.addEventListener('mousedown', this.bodyClick);
    }

    componentWillUnmount() {
        document.removeEventListener('mousedown', this.bodyClick);
    }

    tipContentRef;
    buttonRef;

    toggleTip() {
        this.setState({ tipOpen: !this.state.tipOpen });
    }

    bodyClick(e) {
        if (this.tipContentRef.contains(e.target) || this.buttonRef.contains(e.target)) {
            return;
        }

        this.setState({ tipOpen: false });
    }

    render() {
        const { tipOpen } = this.state;
        return (
            <div className="wrapper">
                <section>
                    <div className="flex-spread">

                        <Tooltip content="you can have compound alignments" direction="up-end" className="target">
                            up-end with arrow
                        </Tooltip>

                    </div>
                </section>
            </div>
        );
    }
}

ReactDOM.render(<App />, document.getElementById('react-root'));