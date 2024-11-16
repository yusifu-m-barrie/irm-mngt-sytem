import { useState } from "react";

const EditEvent = ({ event, onSave }) => {
    const [title, setTitle] = useState(event.title);
    const [description, setDescription] = useState(event.description);

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave({ ...event, title, description });
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>Title</label>
            <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                className="w-full border p-2 my-2"
            />
            <label>Description</label>
            <textarea
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                className="w-full border p-2 my-2"
            />
            <button type="submit" className="bg-green-500 text-white p-2 rounded">
                Save Changes
            </button>
        </form>
    );
};

export default EditEvent;
