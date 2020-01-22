var ToggleButton = React.createClass({
    render: function(){
        return(<button onClick={this.props.openLightbox}>Open Lightbox</button>);
    }
});

var MyPanel = React.createClass({
    onClickSave: function(){
        alert('saved!');
        this.props.closeLightbox();
    },
    render: function(){
        return (
            <div>
                <h3>My Panel</h3>
                <hr />
                <textarea placeholder='Type something here...'></textarea>
                <hr />
                <button onClick={this.onClickSave}>Save</button>
            </div>
        );
    }
});
React.renderComponent(
    <Lightbox>
        <LightboxTrigger>
            <ToggleButton />
        </LightboxTrigger>
        <LightboxModal>
            <MyPanel />
        </LightboxModal>
    </Lightbox>,
    document.getElementById('react-canvas')
);
