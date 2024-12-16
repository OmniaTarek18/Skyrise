export const changePasswordAPI = async (values, actions) => {
  console.log(values);

  const url = `http://localhost:8080/changePassword/${values.id}?password=${values.password}`;
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    actions.setStatus({status:"success", data:json});
    console.log(json);
  } catch (error) {
    console.error(error.message);
  }
  actions.setStatus("fail");

};
