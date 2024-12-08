export const changePasswordAPI = async (values, actions) => {
  console.log(values); // Log form values to the console

  const url = `http://localhost:8080/email?email=${values.email}`;
  try {
    const response = await fetch(url);
    if (!response.ok) {
      actions.setStatus("fail");
      throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    actions.setStatus({status:"success", data:json});
    console.log(json);
  } catch (error) {
    actions.setStatus("fail");
    console.error(error.message);
  }
};
